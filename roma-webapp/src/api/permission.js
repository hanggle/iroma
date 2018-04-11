import request from '@/utils/request'

export function getMenu(userId) {
  const data = {
    userId: userId
  }
  return request({
    url: '/menu/list',
    method: 'post',
    data
  })
}

