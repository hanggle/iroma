<template>
  <div id="areaTree">
    <div class="box-title">
      <a href="#">列表<i class="fa  fa-refresh" @click="freshArea"></i></a>
      <!--<a href="#">列ss表<i class="fa  fa-refresh" @click="sendMsgToParent"></i></a>-->
    </div>
    <div class="tree-box">
      <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
      </div>
    </div>
  </div>
</template>


<script>
  import '../../plugins/ztree/js/jquery.ztree.core.min.js'
  import '../../plugins/ztree/js/jquery.ztree.excheck.min.js'
  import request from '@/utils/request'

  let myVue = {};
  export default {
    name: 'areaTree',
    components: {
    },
    props: {
      showCheckbox: {
        type: Boolean,
        default: true
      },
      expandAll: {
        type: Boolean,
        default: undefined
      },
      url: {
        default: ''
      }
    },
    data: function() {
      return {
        setting: {
          check: {
            enable: myVue.showCheckbox
          },
          callback: {
            onClick: function(event, treeId, treeNode) {
              myVue.$emit('childMsg', treeNode);
            }
          },
          data: {
            key: {
              name: 'name'
            },
            simpleData: {
              enable: true,
              pIdKey: 'pid',
              idKey: 'id',
              rootPId: 1
            }
          }
        },
        zNodes: []
      }
    },
    methods: {
      freshArea: function() {
        loadMenuTree(this);
      },
      sendMsgToParent: function() {
        this.$emit('childMsg', 'childMsg');
      }
    },
    mounted() {
      loadMenuTree(this)
    },
    beforeCreate() {
      myVue = this
    }
  }
  function loadMenuTree(vue) {
    request({
      url: vue.url,
      method: 'get'
    }).then(response => {
      vue.zNodes = response.data
      $.fn.zTree.init($('#treeDemo'), vue.setting, vue.zNodes);
      const treeObj = $.fn.zTree.getZTreeObj('treeDemo');
      if (myVue.expandAll) {
        treeObj.expandAll(myVue.expandAll);
      }
    })
  }
</script>
<style>
  @import '../../plugins/ztree/css/zTreeStyle/zTreeStyle.css';
  #areaTree{
    border:1px solid #e5e5e5;    margin-bottom: 2px;border-radius: 4px;overflow: hidden;
  }
  .box-title{
    border-radius: 3px 3px 0 0;background-color: #f5f5f5;
  }
  .box-title a{
    color: #2fa4e7;
    text-decoration: none;font-size:14px;    display: block;
    padding: 8px 5px;cursor: pointer;
  }
  .box-title .fa{
    float:right;line-height: 20px;
  }
</style>
