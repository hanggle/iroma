<template>
  <div class="createPost-container">
    <el-form class="form-container" :model="postForm" :rules="rules" ref="postForm">

      <sticky :className="'sub-navbar '+postForm.status">
        <template v-if="fetchSuccess">

          <router-link style="margin-right:15px;" v-show='isEdit' :to="{ path:'create-form'}">
            <el-button type="info">创建form</el-button>
          </router-link>

          <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm()">发布
          </el-button>
          <el-button v-loading="loading" type="warning" @click="draftForm">草稿</el-button>

        </template>
        <template v-else>
          <el-tag>发送异常错误,刷新页面,或者联系程序员</el-tag>
        </template>

      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <el-col :span="6">
            <el-input placeholder="菜单名称" style='min-width:250px;' v-model="postForm.menu_name"></el-input>
          </el-col>
          <el-col :span="18">
            <div class="postInfo-container">
                <el-row>
                  <el-col :span="9">
                    <el-tooltip class="item" effect="dark" content="菜单名称" placement="top">
                      <el-form-item label-width="100px" label="菜单名称:" class="postInfo-container-item" prop="menu_name">
                        <el-input placeholder="菜单名称" style='min-width:250px;' v-model="postForm.menu_name">
                        </el-input>
                      </el-form-item>
                    </el-tooltip>
                  </el-col>
                  <el-col :span="9">
                      <el-form-item label-width="100px" label="菜单简称:" class="postInfo-container-item" prop="menu_sname">
                        <el-input placeholder="菜单简称" style='min-width:250px;' v-model="postForm.menu_sname">
                        </el-input>
                      </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="9">
                    <el-form-item label-width="100px" label="path:" class="postInfo-container-item" prop="path">
                      <el-input placeholder="" style='min-width:250px;'  v-model="postForm.path">
                      </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="9">
                      <el-form-item label-width="100px" label="上级菜单:" class="postInfo-container-item" >
                        <multiselect v-model="postForm.parent_id" :options="userLIstOptions" @search-change="getRemoteUserList" placeholder="请选择" selectLabel="选择"
                                     deselectLabel="删除" track-by="key" :internalSearch="false" label="key">
                          <span slot='noResult'>无结果</span>
                        </multiselect>
                      </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="9">
                      <el-form-item label-width="100px" label="图标:" class="postInfo-container-item" prop="icon">
                        <el-input placeholder="图标" style='min-width:250px;' v-model="postForm.icon">
                        </el-input>
                      </el-form-item>
                  </el-col>
                  <el-col :span="9">
                    <el-tooltip class="item" effect="dark" content="一级菜单相隔100，二级菜单取中间值" placement="top">
                      <el-form-item label-width="100px" label="排序编号:" class="postInfo-container-item" prop="order_code">
                        <el-input placeholder="" style='min-width:250px;' v-model.number="postForm.order_code">
                        </el-input>
                      </el-form-item>
                    </el-tooltip>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="9">
                      <el-form-item label-width="100px" label="title:" class="postInfo-container-item">
                        <el-input placeholder="" style='min-width:250px;'  v-model="postForm.title">
                        </el-input>
                      </el-form-item>
                  </el-col>
                  <el-col :span="9">
                      <el-form-item label-width="100px" label="component:" class="postInfo-container-item">
                        <el-input placeholder="" style='min-width:250px;' v-model="postForm.component">
                        </el-input>
                      </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="9">
                    <el-form-item label-width="100px" label="redirect:" class="postInfo-container-item">
                      <multiselect v-model="postForm.redirect" :options="redirectOptions" @search-change="getRedirectList" placeholder="请选择" selectLabel="选择"
                                   deselectLabel="删除" track-by="key" :internalSearch="false" label="key">
                        <span slot='noResult'>无结果</span>
                      </multiselect>
                    </el-form-item>
                  </el-col>
                  <el-col :span="9">
                    <el-form-item label-width="100px" label="iscache:" class="postInfo-container-item">
                      <el-input placeholder="" style='min-width:250px;' v-model="postForm.iscache">
                      </el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="9">
                    <el-form-item label-width="100px" label="描述:" class="postInfo-container-item">
                      <el-input placeholder="" style='min-width:250px;' v-model="postForm.description">
                      </el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              </div>

          </el-col>
        </el-row>
      </div>
    </el-form>

  </div>
</template>

<script>
import Sticky from '@/components/Sticky' // 粘性header组件
import Multiselect from 'vue-multiselect'// 使用的一个多选框组件，element-ui的select不能满足所有需求
import { validateURL } from '@/utils/validate'
import { fetchArticle } from '@/api/article'
import { userSearch } from '@/api/remoteSearch'
import { postform } from '@/api/demo/demoForm'
import { Message } from 'element-ui'
const defaultForm = {
  status: 'draft',
  id: undefined,
  menu_name: '', // 菜单名称
  menu_sname: '', // 菜单名称简称
  path: '', // 菜单路径
  parent_id: '', // 父级id
  order_code: '', // 排序编号
  icon: '',
  title: '', // 前台展示时间
  component: '',
  description: '',
  redirect: '',
  level: ''
}

export default {
  name: 'demoForm',
  components: { Sticky, Multiselect },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: rule.field + '为必传项',
          type: 'error'
        })
        callback(null)
      } else {
        callback()
      }
    }
    const validateSourceUri = (rule, value, callback) => {
      if (value) {
        if (validateURL(value)) {
          callback()
        } else {
          this.$message({
            message: '外链url填写不正确',
            type: 'error'
          })
          callback(null)
        }
      } else {
        callback()
      }
    }
    return {
      postForm: Object.assign({}, defaultForm),
      fetchSuccess: true,
      loading: false,
      userLIstOptions: [],
      platformsOptions: [
        { key: 'a-platform', name: 'a-platform' },
        { key: 'b-platform', name: 'b-platform' },
        { key: 'c-platform', name: 'c-platform' }
      ],
      redirectOptions: [
        { key: '', name: '' },
        { key: 'b-platform', name: 'b-platform' },
        { key: 'c-platform', name: 'c-platform' }
      ],
      rules: {
        menu_name: [{ required: true, message: '请填写菜单名称', trigger: 'blur' }],
        path: [{ required: true, message: '请填写路径', trigger: 'blur' }],
        icon: [{ required: true, message: '请填写图标', trigger: 'blur' }],
        title: [{ validator: validateRequire }],
        content: [{ validator: validateRequire }],
        source_uri: [{ validator: validateSourceUri, trigger: 'blur' }],
        order_code: [{ required: true, message: '请填写排序编号', trigger: 'blur' },
          { type: 'number', message: '编号必须为数字值' }]
      }
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.content_short.length
    }
  },
  created() {
    if (this.isEdit) {
      this.fetchData()
    } else {
      this.postForm = Object.assign({}, defaultForm)
    }
  },
  methods: {
    fetchData() {
      fetchArticle().then(response => {
        this.postForm = response.data
      }).catch(err => {
        this.fetchSuccess = false
        console.log(err)
      })
    },
    submitForm() {
      this.postForm.display_time = parseInt(this.display_time / 1000)
      this.$refs.postForm.validate(valid => {
        if (valid) {
          const data = {
            status: this.postForm.status,
            id: this.postForm.id,
            menuName: this.postForm.menu_name,
            sname: this.postForm.menu_sname,
            path: this.postForm.path,
            parentID: this.postForm.parent_id,
            orderCode: this.postForm.order_code,
            icon: this.postForm.icon,
            title: this.postForm.title,
            component: this.postForm.component,
            description: this.postForm.description,
            redirect: this.postForm.redirect,
            level: this.postForm.level
          }
          console.log(data)
          postform(data).then(response => {
            console.log(response)
            if (!response.data) {
              console.log(response.data)
            }
            // const data = response.data
          }).catch(error => {
            Message.error('Verification failed, please login again')
            Promise.reject(error)
          })
          this.loading = true
          this.$notify({
            title: '成功',
            message: '发布文章成功',
            type: 'success',
            duration: 2000
          })
          this.postForm.status = 'published'
          this.loading = false
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    draftForm() {
      if (this.postForm.content.length === 0 || this.postForm.title.length === 0) {
        this.$message({
          message: '请填写必要的标题和内容',
          type: 'warning'
        })
        return
      }
      this.$message({
        message: '保存成功',
        type: 'success',
        showClose: true,
        duration: 1000
      })
      this.postForm.status = 'draft'
    },
    getRemoteUserList(query) {
      userSearch(query).then(response => {
        if (!response.data.items) return
        console.log(response)
        this.userLIstOptions = response.data.items.map(v => ({
          key: v.name
        }))
      })
    },
    getRedirectList(query) {
      console.log(query)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/mixin.scss";
  .title-prompt{
    position: absolute;
    right: 0px;
    font-size: 12px;
    top:10px;
    color:#ff4949;
  }
  .createPost-container {
    position: relative;
    .createPost-main-container {
      padding: 40px 45px 20px 50px;
      .postInfo-container {
        position: relative;
        @include clearfix;
        margin-bottom: 10px;
        .postInfo-container-item {
          float: left;
        }
      }
      .editor-container {
        min-height: 500px;
        margin: 0 0 30px;
        .editor-upload-btn-container {
            text-align: right;
            margin-right: 10px;
            .editor-upload-btn {
                display: inline-block;
            }
        }
      }
    }
    .word-counter {
      width: 40px;
      position: absolute;
      right: -10px;
      top: 0px;
    }
  }
</style>

