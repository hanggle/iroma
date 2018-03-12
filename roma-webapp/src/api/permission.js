import request from '@/utils/request'

export function getMenu(userId) {
  const data = {
    userId
  }
  return request({
    url: '/system/user/menu',
    method: 'post',
    data
  })
}

