.PHONY: test

# local dev / ci

node_modules: package.json
	npm ci

dev: node_modules
	clojure -A:cider:cljs:shadow watch handler autotest

handler.js: node_modules $(wildcard src/**/*)
	clojure -A:cljs:shadow release handler

test.js: node_modules $(wildcard src/**/*) $(wildcard test/**/*)
	clojure -A:cljs:shadow compile test

node:
	node --inspect handler.js

fmt:
	clojure -A:cljfmt fix

lint:
	clojure -A:kondo --lint src
	clojure -A:cljfmt check

clean:
	rm -rf handler.js handler.js.map test.js

test: test.js
	node test.js

ci: clean lint test

# remote dev / deployment

logs/dev:
	npx serverless logs --function hello --tail

invoke/dev:
	npx serverless invoke --function hello --data '{"hello": "world"}' --log

deploy/dev: handler.js
	npx serverless deploy --verbose

remove/dev:
	npx serverless remove --verbose

deps:
	npm update
	clj -M:antq -m antq.core --upgrade