<template>
  <Modal v-model="modalParam.showModal" :title="modalParam.title">
    <Form ref="sysUser" :model="sysUser" :rules="ruleValidate" :label-width="70">
      <FormItem label="登录名" prop="userName">
        <Input :disabled="disableStatus" type="text" v-model="sysUser.userName"/>
      </FormItem>
      <FormItem label="密码" prop="passWord">
        <Input :disabled="disableStatus" type="password" v-model="sysUser.passWord"/>
      </FormItem>
      <FormItem label="昵称" prop="nickName">
        <Input :disabled="disableStatus" type="text" v-model="sysUser.nickName"/>
      </FormItem>
      <FormItem label="用户类型" prop="userType">
        <Select :disabled="disableStatus" v-model="sysUser.userType" placeholder="请选择用户类型" filterable>
          <Option :value="1" :key="1">注册用户</Option>
        </Select>
      </FormItem>
      <FormItem label="性别" prop="sex">
        <Select :disabled="disableStatus" v-model="sysUser.sex" placeholder="请选择性别" filterable>
          <Option :value="1" :key="1">男</Option>
          <Option :value="2" :key="2">女</Option>
        </Select>
      </FormItem>
      <FormItem label="手机号" prop="mobile">
        <Input :disabled="disableStatus" type="text" v-model="sysUser.mobile"/>
      </FormItem>
      <FormItem label="邮箱" prop="email">
        <Input :disabled="disableStatus" type="text" v-model="sysUser.email"/>
      </FormItem>
      <FormItem label="状态" prop="status">
        <Select :disabled="disableStatus" v-model="sysUser.status" placeholder="请选择状态" filterable>
          <Option :value="1">正常</Option>
          <Option :value="2">停用</Option>
        </Select>
      </FormItem>
      <FormItem label="证件号" prop="cardNo">
        <Input :disabled="disableStatus" type="text" v-model="sysUser.cardNo"/>
      </FormItem>
    </Form>
    <div slot="footer">
      <Button type="text" size="large" @click="modalCancel('sysUser', 'showModal')">取消</Button>
      <Button :disabled="disableStatus" type="primary" size="large" @click="modalOk('sysUser', 'showModal')">确定</Button>
    </div>
  </Modal>
</template>

<script>
import * as apis from '@/api/data'
export default {
  props: {
    modalParam: Object
  },
  data () {
    const checkMobile = (rule, value, callback) => {
      let reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/
      if (!reg.test(value)) {
        callback(new Error('请输入正确的手机号'))
      } else {
        callback()
      }
    }
    const checkCardNo = (rule, value, callback) => {
      let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (value !== '') {
        if (!reg.test(value)) {
          callback(new Error('请输入正确的身份证号'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    return {
      disableStatus: false,
      sysUser: {
        userName: '',
        passWord: '',
        nickName: '',
        userType: '',
        mobile: '',
        sex: '',
        email: '',
        status: '',
        cardNo: ''
      },
      ruleValidate: {
        userName: [
          { required: true, message: 'The name cannot be empty', trigger: 'blur' },
          { type: 'string', min: 6, message: 'name no less than 8 words', trigger: 'blur' }
        ],
        passWord: [
          { required: true, message: 'The password cannot be empty', trigger: 'blur' },
          { type: 'string', min: 6, message: 'password no less than 8 words', trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: 'The mobile cannot be empty', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ],
        cardNo: [
          { validator: checkCardNo, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    modalOk (name, showModal) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          apis.saveSysUser(this.sysUser).then(res => {
            this.modalCancel(name, showModal)
            this.$Message.success(res.data.message)
            this.$emit('refreshTable')
          }).catch(err => {
            this.$Message.error(err.message)
          })
        }
      })
    },
    modalCancel (name, showModal) {
      let newObj = JSON.parse(JSON.stringify(this.modalParam))
      newObj[showModal] = !newObj[showModal]
      this.$emit(`update:modalParam`, newObj)
      this.$refs[name].resetFields()
    }
  },
  watch: {
    'modalParam.showModal': function () {
      if (this.modalParam.showModal === true) {
        if (this.modalParam.operate === 0 || this.modalParam.operate === 2) {
          apis.getSysUserById(this.modalParam.id).then(res => {
            this.sysUser = res.data.data
            if (this.modalParam.operate === 0) {
              this.disableStatus = true
            } else {
              this.disableStatus = false
            }
          }).catch(err => {
            this.$Message.error(err.message)
          })
        } else {
          this.disableStatus = false
        }
      }
    }
  }
}
</script>
