(ns supabase-alive.core
  (:gen-class)
  (:require [clj-http.client :as http]
            [cheshire.core :as json]))




(def supabase-url "https://rewckqeamybaijuwyiox.supabase.co/rest/v1")
(def service-key (System/getenv "SERVICE_KEY"))

(defn get-users []
  ;; The table name is `User` in Supabase.
  (let [resp (http/get
              (str supabase-url "/User")
              {:headers {"apikey" service-key
                         "Authorization" (str "Bearer " service-key)}
               :query-params {"select" "*"}
               :as :json})]
    (:body resp)))

(defn -main [& _]
  (println "Starting Supabase Alive...")
  (let [users (get-users)]
    (println "Fetched users:")
    (doseq [u users]
      (println "typeJ" (type u))
      (prn (:email u))
      (println u))))