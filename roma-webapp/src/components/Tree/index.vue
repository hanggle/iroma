<template>
  <div>
    <template v-if="showCheckbox">
      <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
    </template>
    <template v-else>
      <el-tree :data="data" :props="defaultProps" show-checkbox @node-click="handleNodeClick"></el-tree>
    </template>
  </div>
</template>

<script>

export default {
  name: 'tree',
  components: { },
  props: {
    showCheckbox: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      data: [{
        label: '一级 1',
        id: 1,
        children: [{
          label: '二级 1-1',
          children: [{
            label: '三级 1-1-1'
          }]
        }]
      }, {
        label: '一级 2',
        children: [{
          label: '二级 2-1',
          children: [{
            label: '三级 2-1-1'
          }]
        }, {
          label: '二级 2-2',
          children: [{
            label: '三级 2-2-1'
          }]
        }]
      }, {
        label: '一级 3',
        children: [{
          label: '二级 3-1',
          children: [{
            label: '三级 3-1-1'
          }]
        }, {
          label: '二级 3-2',
          children: [{
            label: '三级 3-2-1'
          }]
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  created() {
  },
  methods: {
    handleNodeClick(data) {
      // this.p_ID = data.
      console.log(this.$treeNodeId)
      console.log(data)
      this.flatState.filter(function(e) {
        if (e.node.indeterminate) {
          console.log(e.node.indeterminate)
        }
      })
    }
  }
}
</script>

