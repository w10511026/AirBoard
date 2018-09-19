import axios from '@/libs/api.request'

export const getTableData = () => {
  return axios.request({
    url: 'get_table_data',
    method: 'get'
  })
}

export const getDragList = () => {
  return axios.request({
    url: 'get_drag_list',
    method: 'get'
  })
}

/* ------------------------------ 系统管理 ------------------------------ */
/* * 系统用户分页列表  **/
export const listPageSysUser = (param) => {
  return axios.request({
    url: '/sysUser/listPage',
    method: 'get',
    params: param
  })
}

/* * 删除用户  **/
export const deleteSysUserByIds = (param) => {
  return axios.request({
    url: '/sysUser/deleteByIds',
    method: 'post',
    data: param
  })
}
/* ------------------------------ HotSaleProduct ------------------------------ */
