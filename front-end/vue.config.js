module.exports = {
  lintOnSave:false,
  productionSourceMap: false,
  devServer: {
    proxy: {
        //会把请求路径中的/api换为后面的代理服务器，上线环境
        '/api': {
            //提供数据的服务器地址
            target: 'http://101.200.234.105:9090',

        }
    },
}

// devServer: {
//   proxy: {
//       //会把请求路径中的/api换为后面的代理服务器，调试环境
//       '/api': {
//           //提供数据的服务器地址
//           target: 'http://localhost:9090',
//
//       }
//   },
// }
  }