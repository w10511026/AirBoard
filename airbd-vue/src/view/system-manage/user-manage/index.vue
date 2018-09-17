<template>
  <div>
    <Card>
      <Form style="width: 300px">
        <FormItem>
          <Row>
            <Col span="18">
              <Input type="text" v-model="userName" placeholder="Enter something..."><span slot="prepend">登录名</span></Input>
            </Col>
            <Col span="4" offset="1">
              <Button type="primary" @click="searchList()">搜索</Button>
            </Col>
          </Row>
        </FormItem>
      </Form>
      <ButtonGroup>
        <Button icon="md-add"></Button>
        <Button icon="md-close"></Button>
        <Button icon="md-create"></Button>
      </ButtonGroup>
      <Table :loading="loading" :data="tableData" :columns="columns" stripe/>
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
      loading: true,
      userName: ''
    }
  },
  methods: {
    searchList (params) {
      let id = params.row.id
      console.log(id)
    },
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
      this.loading = false
    })
  }
}
</script>

<style>

</style>
