# flowable-study
# 1.项目构成  
  项目采用了前后端分离的结构  
  后端：flowable   
  前端：flowable-web  
# 2.部署方式  
  环境准备:nginx  
  1.把项目下载下来  
  2.把后端项目用idea或别的开发工具打开，在本地mysql创建数据库test,并运行resource/database下的sql文件  
  3.启动，web端口配置的是8090  
  4.在nginx配置文件中配置，配置文件如下：  
  http {  
      include mime.types;  
      default_type  application/octet-stream;  
      server {  
          listen       8808;  
          charset UTF-8;  
          location /flowable/{  
             proxy_pass http://localhost:8090/;  #后端访问地址  
          }  
          location / {  
            root C:/work/flowable-study/flowable-web;  #web端位置  
            index login.html;  
          }  
      }  
  }  
  启动之后通过http://127.0.0.1:8808/  访问即可  
