# serverless-demo

A demo of using clojurescript for aws lambda functions.

The choice of make is because it's good at orchestrating multiple cli
commands and has auto-completion.

## local dev

To start local development, do:

    make dev # kick off a shadow-cljs watch for handler.js

Then in another terminal, do:

    make node # start remote node runtime for repl

## formatting

To format source code, do:
    
    make fmt

## static analysis

To run all static analysis checks, do:

    make ci

This should be the exact target used during continuous integration.

## remote dev

To deploy the lambda to aws, do:

    make deploy/dev

To invoke the remote lambda, do:

    make invoke/dev

To view remote logs, do:
    
    make logs/dev

Only the handler.js, handler.js.map and production npm dependencies are
included in the deployed package. Source maps are bundled into the package
zip to improve stack trace debugging.
