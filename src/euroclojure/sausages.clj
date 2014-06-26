(ns euroclojure.sausages
  (:require [schema.core :as s]))

;;
;; The Schemas
;;

(s/defschema Origin {:country String
                     (s/optional-key :city) String})

(def Ingredient (s/enum :pork :beef :blood :pepper :garlic :rye :flour))

(s/defschema Sausage {:id Long
                      :name String
                      :description String
                      :origin Origin
                      (s/optional-key :ingredients) #{Ingredient}})

(s/defschema NewSausage (dissoc Sausage :id))

;;
;; Origins
;;

(def poland (s/validate Origin {:country "Poland"}))

(def tampere (s/validate Origin {:country "Finland" :city "Tampere"}))

;;
;; Sausages
;;

(def bydgoska
  (s/validate NewSausage
              {:name "Bydgoska"
               :description "Smoked and steamed sausage"
               :origin (assoc poland :city "Bydgoszcz")
               :ingredients #{:pork :beef}}))

(def krakowska
  (s/validate NewSausage
              {:name "Krakowska"
               :description "Polish sausage (kielbasa), usually served as a cold cut"
               :origin (assoc poland :city "Krakow")
               :ingredients #{:pork :pepper :garlic}}))

;;
;; The Database
;;

(def ids (atom 0))
(def sausages (atom {}))

(s/defn ^:always-validate add-sausage! :- Sausage [new-sausage :- NewSausage]
  (let [id (swap! ids inc)
        sausage (assoc new-sausage :id id)]
    (swap! sausages assoc id sausage)
    sausage))

(s/defn ^:always-validate get-sausage :- (s/maybe Sausage) [id :- Long]
  (@sausages id))

(s/defn ^:always-validate get-sausages :- [Sausage] []
  (vals @sausages))

(defn init! []
  (reset! sausages {})
  (reset! ids 0)
  (add-sausage! bydgoska)
  (add-sausage! krakowska)
  nil)

(init!)
