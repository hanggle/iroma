<template>
    <form-detail :is-edit='false'></form-detail>
</template>

<script>
import formDetail from './components/PersonDetail'

export default {
  name: 'personForm',
  components: { formDetail }
}

</script>

