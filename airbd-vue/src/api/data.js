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
/* * 用户管理  **/
export const listPageSysUser = () => {
  return axios.request({
    url: '/sysUser/listPage',
    method: 'get'
  })
}
/* ------------------------------ HotSaleProduct ------------------------------ */
