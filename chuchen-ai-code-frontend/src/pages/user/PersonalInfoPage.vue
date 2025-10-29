<template>
  <h2>个人信息</h2>

</template>

<script setup lang="ts">
import { useLoginUserStore } from '@/stores/loginUser.ts'

const loginUserStore = useLoginUserStore()

console.log(loginUserStore.loginUser)
</script>

<style scoped></style>
