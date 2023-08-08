(ns serverless-demo.core
  (:require ["source-map-support/register"]))

(defn hello [event]
  (js/Promise.resolve
   #js {:statusCode 200
        :body
        (js/JSON.stringify
         #js {:message "Go Serverless v1.0! Your function executed successfully!"
              :input event} nil 2)}))

(def exports #js {:hello hello})

(comment
  ;; useful debugging tips
  (require 'portal.api)
  (add-tap #'portal.api/submit)
  (portal.api/open)

  ;; good old println should work fine
  (println {:hello :world})

  ;; use a debugger statement if you want to use the chrome inspector
  ;; source maps should be setup to work correctly
  (let [value 1] (js-debugger) value)

  ;; send values to the shadow-cljs inspect
  ;; goto http://localhost:9630/inspect to view tap log
  (tap> {:hello :world})

  ;;resolve a promise value before printing
  (.then (hello #js {}) println))

