(ns euroclojure.simple-api
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [euroclojure.sausages :refer :all]))

(defapi app
  (context "/sausages" []
    (GET* "/" []
      :return [Sausage]
      (ok (get-sausages)))
    (GET* "/:id" []
      :return Sausage
      :path-params [id :- Long]
      (ok (get-sausage id)))
    (POST* "/" []
      :return Sausage
      :body [new-sausage NewSausage]
      (ok (add-sausage! new-sausage)))))
