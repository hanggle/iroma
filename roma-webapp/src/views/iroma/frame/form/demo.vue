<template>
    <form-detail :is-edit='false'></form-detail>
</template>

<script>
import formDetail from './components/demoForm'

export default {
  name: 'demoForm',
  components: { formDetail }
}
</script>

