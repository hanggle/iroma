import request from '@/utils/request'

export function postform(data) {
  return request({
    url: '/menu',
    method: 'post',
    data
  })
}
