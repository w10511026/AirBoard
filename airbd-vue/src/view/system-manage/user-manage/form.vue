<template>
  <div>
    <Modal v-model="display" title="操作">
      <Form ref="sysUser" :model="sysUser" :rules="ruleValidate" :label-width="70">
        <FormItem label="登录名" prop="userName">
          <Input type="text" v-model="sysUser.userName"/>
        </FormItem>
        <FormItem label="密码" prop="passWord">
          <Input type="password" v-model="sysUser.passWord"/>
        </FormItem>
        <FormItem label="昵称">
          <Input type="text" v-model="sysUser.nickName"/>
        </FormItem>
        <FormItem label="用户类型">
          <Select v-model="sysUser.userType" placeholder="请选择用户类型" filterable>
            <Option value="1">注册用户</Option>
          </Select>
        </FormItem>
        <FormItem label="性别">
          <Select v-model="sysUser.sex" placeholder="请选择性别" filterable>
            <Option value="1">男</Option>
            <Option value="2">女</Option>
          </Select>
        </FormItem>
        <FormItem label="手机号" prop="mobile">
          <Input type="text" v-model="sysUser.mobile"/>
        </FormItem>
        <FormItem label="邮箱">
          <Input type="text" v-model="sysUser.email"/>
        </FormItem>
        <FormItem label="状态">
          <Select v-model="sysUser.status" placeholder="请选择状态" filterable>
            <Option value="1">正常</Option>
            <Option value="2">停用</Option>
          </Select>
        </FormItem>
        <FormItem label="证件号" prop="cardNo">
          <Input type="text" v-model="sysUser.cardNo"/>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
export default {
  props: {
    display: false
  },
  data () {
    const validateMobile = (rule, value, callback) => {
      let reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/
      if (!reg.test(value)) {
        callback(new Error('请输入正确的手机号'))
      }
    }
    const validateCardNo = (rule, value, callback) => {
      let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (value !== '') {
        if (!reg.test(value)) {
          callback(new Error('请输入正确的身份证号'))
        }
      }
    }
    return {
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
          { validator: validateMobile, trigger: 'blur' }
        ],
        cardNo: [
          { validator: validateCardNo, trigger: 'blur' }
        ]
      }
    }
  }
}
</script>
