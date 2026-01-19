<template>
  <a-layout-header class="header">
    <a-row :wrap="false" align="middle" style="height: 100%">
      <!-- 左侧 Logo + 标题 固定宽度 -->
      <a-col :flex="'0 0 260px'">
        <RouterLink to="/">
          <div class="header-left">
            <img class="logo" src="@/assets/logo.png" alt="Logo" />
            <h1 class="site-title">大骨节病AI预测系统</h1>
          </div>
        </RouterLink>
      </a-col>

      <!-- 中间菜单 - 真正占满剩余空间并居中 -->
      <a-col :flex="1" style="display: flex; justify-content: center">
        <div style="display: flex; justify-content: center; width: 100%" :key="forceRenderKey">
          <a-menu
            v-model:selectedKeys="selectedKeys"
            mode="horizontal"
            :items="menus"
            @click="handleMenuClick"
          />
        </div>
      </a-col>

      <!-- 右侧 用户区域 固定宽度或自适应 -->
      <a-col :flex="'0 0 260px'">
        <div class="user-login-status">
          <!-- ... 原有用户登录/头像部分保持不变 ... -->
          <div v-if="loginUserStore.loginUser.id" class="user-info">
            <a-dropdown>
              <a-space>
                <a-avatar :src="loginUserStore.loginUser.userAvatar" />
                {{ loginUserStore.loginUser.userName ?? '无名' }}
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item @click="doInformation"> <UserOutlined /> 个人信息 </a-menu-item>
                  <a-menu-item @click="doLogout"> <LogoutOutlined /> 退出登录 </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </a-layout-header>
</template>

<script setup lang="ts">
import { computed, h, ref } from 'vue'
import { type RouteRecordRaw, useRoute, useRouter } from 'vue-router'
import { type MenuProps, message } from 'ant-design-vue'
// JS 中引入 Store
import { useLoginUserStore } from '@/stores/loginUser.ts'

const loginUserStore = useLoginUserStore()
const router = useRouter()
// 当前选中菜单
const selectedKeys = ref<string[]>(['/'])
// 监听路由变化，更新当前选中菜单
router.afterEach((to) => {
  selectedKeys.value = [to.path]
})

import { LogoutOutlined, HomeOutlined, UserOutlined } from '@ant-design/icons-vue'
import { userLogout } from '@/api/userController.ts'
import checkAccess from '@/access/checkAccess.ts'

// 用户注销
const doLogout = async () => {
  const res = await userLogout()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success('退出登录成功')
    await router.push('/user/login')
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}

const doInformation = async () => {
  await router.push('/user/information')
}

const route = useRoute()
const forceRenderKey = computed(() => route.path + Date.now().toString().slice(-6))

// 菜单配置项
const originMenus = [
  {
    key: '/',
    icon: () => h(HomeOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/admin/userManage',
    label: '用户管理',
    title: '用户管理',
  },
]
// eslint-disable-next-line @typescript-eslint/no-explicit-any
const menuToRouteItem = (menu: any): RouteRecordRaw => {
  // 获取所有路由
  const routes = router.getRoutes()
  // 根据菜单的key查找对应的路由
  const route = routes.find((route) => route.path === menu.key)
  // 如果找到对应路由则返回，否则返回一个默认的空路由对象
  return route || ({} as RouteRecordRaw)
}

// 过滤菜单项
const filterMenus = (menus = [] as MenuProps['items']) => {
  // 过滤条件是一个回调函数 (menu) => { ... }, 返回 true 表示保留该菜单项，返回 false 表示过滤掉该菜单项
  return menus?.filter((menu) => {
    // 通过menu的key值找到对应的路由字段
    const item = menuToRouteItem(menu)

    // 如果是菜单项中没有对应的路由，就说明该菜单是自定义的，予以保留，返回true
    if (!item.path) {
      return true
    }

    // 如果有hideInMenu标记为true，则隐藏
    if (item.meta?.hideInMenu) {
      return false
    }

    // 根据权限过滤菜单，有权限则返回true，会保留该菜单
    return checkAccess(loginUserStore.loginUser, item.meta?.access as string)
  })
}

// 展示在菜单的路由数组
const menus = computed(() => {
  return filterMenus(originMenus)
})

// 处理菜单点击
const handleMenuClick: MenuProps['onClick'] = (e) => {
  const key = e.key as string
  selectedKeys.value = [key]
  // 跳转到对应页面
  if (key.startsWith('/')) {
    router.push(key)
  }
}
</script>

<style scoped>
.header {
  background: #fff;
  padding: 0 24px;
  height: 64px; /* 建议显式指定 header 高度 */
  line-height: 64px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo {
  height: 60px; /* 建议稍微调小一点更协调 */
  width: 60px;
}

.site-title {
  margin: 0;
  font-size: 20px;
  color: #1890ff;
}

.menu-wrapper {
  width: 80%;
  margin: auto;
}

.user-login-status {
  display: flex;
  align-items: center;
  height: 100%;
}

.user-info:hover {
  cursor: pointer;
}

.ant-menu-horizontal {
  border-bottom: none !important;
  background: transparent;
}
</style>
