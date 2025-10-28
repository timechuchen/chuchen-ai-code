<template>
  <BasicLayout />
</template>

<script setup lang="ts">
import BasicLayout from '@/layouts/BasicLayout.vue'
import { healthCheck } from '@/api/healthController.ts'

healthCheck().catch(err => {
  console.log(err)
})
</script>

<style></style>
