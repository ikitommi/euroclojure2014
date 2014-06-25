(ns euroclojure.simple
  (:require [compojure.handler :as handler]
            [compojure.core :refer :all]
            [ring.util.http-response :refer :all]
            [ring.middleware.format :as format]))

(defroutes app-routes
  (context "/api" []

    (GET "/ping" []
      (ok {:ping "pong"}))

    (POST "/sausage" {sausage :body-params}
      (ok (assoc sausage :id 1)))))

(def app
  (-> app-routes
      handler/api
      (format/wrap-restful-format :formats [:json-kw])))
