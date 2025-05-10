# sge-facom-back-clj

Para o deploy, apenas rodar:

Precisa do `sudo` pois só o root pode usar portas <1024.

```bash
sudo env PORT=80 nohup java -jar app.jar > app.log 2>&1 &
```
