import request from '@/utils/request'

export function postform(data) {
  return request({
    url: '/menu',
    method: 'post',
    data
  })
}
export function getMenu(preId) {
  const data = {
    preId: preId
  }
  return request({
    url: '/menu/list',
    method: 'post',
    data
  })
}
