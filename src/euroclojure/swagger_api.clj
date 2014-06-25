(ns euroclojure.swagger-api
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [euroclojure.sausages :refer :all]))

(defapi app
  (swagger-ui)
  (swagger-docs
    :title "Makkara-api"
    :description "Finnish & Polish sausages on sale.")
  (swaggered "sausage"
    :description "this is the sausage api"
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
        (ok (add-sausage! new-sausage))))))
