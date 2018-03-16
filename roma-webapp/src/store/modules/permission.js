import { asyncRouterMap, constantRouterMap } from '@/router'
import { getMenu } from '@/api/permission'
const _import = require('../../router/_import_' + process.env.NODE_ENV)

import Layout from '../../views/layout/Layout'

/**
 * 动态加载菜单
 * @param roles
 * @param route
 */
function loadMenu(asyncRouterMap, menus) {
  asyncRouterMap = []
  if (menus) {
    for (let i = 0; i < menus.length; i++) {
      const oneLevel = menus[i]
      if (oneLevel.level === '1') {
        const oneMenu = {}
        const oneMeta = {}
        const oneChild = []
        oneMenu.path = oneLevel.path
        oneMenu.component = Layout
        oneMenu.name = oneLevel.menuName
        oneMenu.title = oneLevel.title
        oneMeta.title = oneLevel.title
        oneMeta.icon = oneLevel.icon
        oneMenu.meta = oneMeta
        if (oneLevel.isMenu === '1') {
          for (let j = 0; j < menus.length; j++) {
            const twoLevel = menus[j]
            if (twoLevel.parentId === oneLevel.id) {
              const twoMenu = {}
              const twoMeta = {}
              // const twoChild = []
              twoMenu.path = twoLevel.path
              twoMenu.component = _import(twoLevel.component)
              twoMenu.name = twoLevel.menuName
              twoMeta.title = twoLevel.title
              twoMeta.icon = twoLevel.icon
              twoMenu.meta = twoMeta
              // 三级菜单暂时不用
              /*
              if (twoLevel.isMenu === '1') {
                for (let z = 0; z < menus.length; z++) {
                  const threeLevel = menus[z]
                  if (threeLevel.parentId === twoLevel.menuId) {
                    const threeMenu = {}
                    const threeMeta = {}
                    threeMenu.path = threeLevel.path
                    threeMenu.component = _import(threeLevel.component)
                    threeMenu.name = threeLevel.menuName
                    threeMeta.title = threeLevel.title
                    threeMeta.icon = threeLevel.icon
                    threeMenu.meta = threeMeta
                    twoChild.push(threeMenu)
                  }
                }
              }
              */
              oneChild.push(twoMenu)
            }
          }
        }
        oneMenu.children = oneChild
        asyncRouterMap.push(oneMenu)
      }
    }
  }
  return asyncRouterMap
}

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise((resolve, reject) => {
        if (data.data.username === 'adminmenu') {
          commit('SET_ROUTERS', asyncRouterMap)
          resolve()
        } else {
          getMenu(data).then(response => {
            const accessedRouters = loadMenu(asyncRouterMap, response.data)
            commit('SET_ROUTERS', accessedRouters)
            // commit('SET_ROUTERS', data)
            resolve(response)
          }).catch(error => {
            reject(error)
          })
        }
      })
    }
  }
}

export default permission
