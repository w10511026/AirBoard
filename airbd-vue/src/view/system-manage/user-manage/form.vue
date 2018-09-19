<template>
  <div>
    <Modal v-model="display" title="formName">
      <Form ref="sysUser" :model="sysUser" :label-width="80">
        <FormItem label="登录名">
          <Input type="text" v-model="sysUser.userName"/>
        </FormItem>
        <FormItem label="密码">
          <Input type="password" v-model="sysUser.passWord"/>
        </FormItem>
        <FormItem>
          <Button type="primary">保存</Button>
          <Button style="margin-left: 8px">取消</Button>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
export default {
  props: {
    display: false,
    formName: ''
  },
  data () {
    return {
      formData: [],
      total: 0,
      loading: true,
      selection: [],
      sysUser: {
        userName: '',
        passWord: ''
      }
    }
  },
  methods: {
    handleSubmit () {

    }
  }
}
</script>
