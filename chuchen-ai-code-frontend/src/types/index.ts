import type { VNode } from 'vue'

interface MenuItem {
  key: string
  icon?: () => VNode
  label: string
  title: string
}

export type { MenuItem }

