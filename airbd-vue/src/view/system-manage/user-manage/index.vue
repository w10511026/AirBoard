<template>
  <div>
    <Card>
      <Form ref="formInline" :model="sysUser">
        <Input v-model="sysUser.userName" placeholder="Enter name" style="width: auto"><span slot="prepend">登录名</span></Input>
        <Button class="search-btn" type="primary"><Icon type="search"/>&nbsp;&nbsp;搜索</Button>
      </Form>
      <tables ref="tables" editable v-model="tableData" :columns="columns"
              @on-delete="handleDelete"/>
      <div style="margin: 10px 0px; overflow: hidden">
        <Button type="primary" @click="exportExcel">导出为Csv文件</Button>
        <div style="float: right;">
          <Page :total="total" :current="1" @on-change="changePage"></Page>
        </div>
      </div>
    </Card>
  </div>
</template>

<script>
import {format} from '@/libs/tools'
import Tables from '_c/tables'
import * as apis from '@/api/data'
export default {
  name: 'tables_page',
  components: {
    Tables
  },
  data () {
    return {
      columns: [
        {type: 'selection', width: 60, key: 'id'},
        {title: '登录名称', key: 'userName'},
        {title: '昵称', key: 'nickName', editable: true},
        {title: '用户类型', key: 'userTypeZh'},
        {title: '手机号', key: 'mobile'},
        {title: '性别', key: 'sexZh'},
        {title: '邮箱', key: 'email'},
        {title: '状态', key: 'statusZh'},
        {title: '证件', key: 'cardNo'},
        {title: '创建时间', key: 'createTime', sortable: true, render: (h, params) => { return h('div', format(params.row.createTime)) }},
        {
          title: '操作',
          key: 'handle',
          options: ['delete'],
          button: [
            (h, params, vm) => {
              return h('Poptip', {
                props: {
                  confirm: true,
                  title: '你确定要删除吗?'
                },
                on: {
                  'on-ok': () => {
                    vm.$emit('on-delete', params)
                    vm.$emit('input', params.tableData.filter((item, index) => index !== params.row.initRowIndex))
                  }
                }
              })
            }
          ]
        }
      ],
      tableData: [],
      total: 0,
      sysUser: []
    }
  },
  methods: {
    handleDelete (params) {
      let id = params.row.id
      console.log(id)
    },
    handleView (params) {
      let id = params.row.id
      console.log(id)
    },
    exportExcel () {
      this.$refs.tables.exportCsv({
        filename: `table-${(new Date()).valueOf()}.csv`
      })
    },
    changePage () {

    }
  },
  mounted () {
    apis.listPageSysUser().then(res => {
      this.tableData = res.data.data
      this.total = res.data.total
      this.sysUser = res.data.data
    })
  }
}
</script>

<style>

</style>
