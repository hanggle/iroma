<template>
    <menu-detail :is-edit='false'></menu-detail>
</template>

<script>
import menuDetail from './components/MenuDetail'

export default {
  name: 'MenuDetail',
  components: { menuDetail }
}
</script>

