<template>
  <div>
    <Card>
      <Form style="width: 600px">
        <FormItem>
          <Row :gutter="16">
            <Col span="8">
            <Input type="text" v-model="params.userName" placeholder="登录名"/>
            </Col>
            <Col span="8">
            <Input type="text" v-model="params.mobile" placeholder="手机"/>
            </Col>
            <Col span="8">
            <ButtonGroup>
              <Button type="primary" icon="logo-google" @click="handleSearch">搜索</Button>
              <Button icon="ios-backspace-outline" @click="handleReset">重置</Button>
            </ButtonGroup>
            </Col>
          </Row>
        </FormItem>
      </Form>
      <div>
        <ButtonGroup>
          <Button icon="md-add" @click="handleOpenForm">新增</Button>
          <Button icon="md-create" @click="handleOpenForm">修改</Button>
          <Button icon="md-close" @click="handleDelete">删除</Button>
        </ButtonGroup>
      </div>
      <Table ref="tables" :loading="loading" :data="tableData" :columns="columns" highlight-row
             @on-selection-change="handleSelect" @on-row-dblclick="handleDbClick"/>
      <div style="overflow: hidden">
        <Button @click="exportExcel" style="margin: 10px 0px;">导出Csv</Button>
        <div style="float: right;margin: 10px 0px;">
          <Page :total="total" :current="params.pageIndex" :page-size="params.pageSize" show-sizer show-total @on-change="handlePage" @on-page-size-change='handlePageSize'></Page>
        </div>
      </div>
    </Card>
    <MyForm v-model="iModal" @refreshTable="handleSearch"/>
  </div>
</template>

<script>
import {format} from '@/libs/tools'
import Tables from '_c/tables'
import * as apis from '@/api/data'
import MyForm from '@/view/system-manage/user-manage/form'

export default {
  components: {
    Tables,
    MyForm
  },
  data () {
    return {
      tableData: [],
      total: 0,
      iModal: false,
      loading: true,
      selection: [],
      params: {
        pageIndex: 1,
        pageSize: 10,
        userName: '',
        mobile: ''
      },
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
        {title: '创建时间', key: 'createTime', sortable: true, render: (h, params) => { return h('div', format(params.row.createTime)) }}
      ]
    }
  },
  methods: {
    handleSearch () {
      apis.listPageSysUser(this.params).then(res => {
        this.tableData = res.data.data
        this.total = res.data.total
        this.loading = false
      })
    },
    handleReset () {
      this.params = {}
    },
    handleSelect (selection) {
      this.selection = selection
    },
    handleOpenForm () {
      this.iModal = true
    },
    handleDelete () {
      this.$Modal.confirm({
        title: '确定删除吗？',
        onOk: () => {
          let arr = []
          this.selection.forEach(function (row) {
            arr.push(row.id)
          })
          if (arr.length > 0) {
            apis.deleteSysUserByIds(arr).then(res => {
              this.$Message.info(res.data.message)
              this.handleSearch()
            }).catch(err => {
              this.$Message.error(err.message)
            })
          }
        }
      })
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
    handlePage (value) {
      this.params.pageIndex = value
      this.handleSearch()
    },
    handlePageSize (value) {
      this.params.pageSize = value
      this.handleSearch()
    },
    handleDbClick (value) {
      console.info(value)
    }
  },
  mounted () {
    this.handleSearch()
  }
}
</script>

<style>

</style>
