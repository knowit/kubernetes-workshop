build:
	#docker run --rm -v `pwd`/source:/home/gradle/project -w /home/gradle/project gradle gradle build 
	cd source && ./gradlew build
	docker build -t ubuntu-k8s-1:30603/sample-app .

push:
	docker push ubuntu-k8s-1:30603/sample-app

