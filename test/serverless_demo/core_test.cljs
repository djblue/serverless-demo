(ns serverless-demo.core-test
  (:require [clojure.test :refer [deftest is async]]
            [serverless-demo.core :as demo]))

(deftest hello-handler-test
  (async done
         (.then (demo/hello {})
                (fn [res]
                  (is (= res.statusCode 200))
                  (done)))))

