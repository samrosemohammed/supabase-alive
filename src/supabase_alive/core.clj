(ns supabase-alive.core
  (:gen-class)
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            [supabase-alive.config :as config]
            ))




(def supabase-url "https://nmvaereuafhivpvlesse.supabase.co/rest/v1")
(def service-key config/api-key)

(defn get-schools []
  ;; The table name is `User` in Supabase.
  (let [resp (http/get
              (str supabase-url "/schools")
              {:headers {"apikey" service-key
                         "Authorization" (str "Bearer " service-key)}
               :query-params {"select" "*"}
               :as :json})]
    (:body resp)))

(defn -main [& _]
  (println "Starting Supabase Alive...")
   (get-schools)
  )
