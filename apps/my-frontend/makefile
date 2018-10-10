# $ docker build -t <your username>/my-frontend .
build:
	docker build . -t my-frontend

run:
	docker run -p 8080:8080 -e BACKEND_URL=NOT_SET my-frontend

run-local:
	export BACKEND_URL=NOT_SET_LOCAL && \
		npm run start
