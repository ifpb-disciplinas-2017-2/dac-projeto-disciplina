sudo docker build -t projetodac/web .
#Ainda sem a linkagem do Postgres
sudo docker run -p 8081:8080 -d --name dac projetodac/web
