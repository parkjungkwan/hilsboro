const {createProxyMiddleware} = require('http-proxy-middleware');
module.exports = (app) => { 
  app.use(
    createProxyMiddleware(
      '/api', 
      { target: "http://13.209.194.227", 
      changeOrigin: true }) 
      ); 
    };