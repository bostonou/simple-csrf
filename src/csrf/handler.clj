(ns csrf.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [hiccup.core :refer [html]]
            [hiccup.form :refer [form-to submit-button text-field]]))

(defroutes app-routes
  (GET "/" []
    (html
      (form-to [:post "/"]
               (anti-forgery-field)
               (text-field "text-field" "Your Value")
               (submit-button "Submit"))))
  (POST "/" req
    (pr-str (:params req)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
