(ns darkexchange.view.main.peer-tab
  (:require [clojure.contrib.logging :as logging]
            [darkexchange.model.terms :as terms]
            [seesaw.core :as seesaw-core])
  (:import [javax.swing.table DefaultTableColumnModel TableColumn]))

(def tab-name (terms/peer))

(def peer-table-columns [ { :key :destination :text (terms/destination) }
                          { :key :created_at :text (terms/created-on) }
                          { :key :updated_at :text (terms/last-updated-at) }
                          { :key :notified :text (terms/notified) }])

(defn create-destination-text-area []
  (let [text-area (seesaw-core/text
                    :id :destination-text
                    :multi-line? true
                    :editable? false
                    :preferred-size [400 :by 100])]
    (.setLineWrap text-area true)
    text-area))

(defn create-destination-text []
  (seesaw-core/scrollable (create-destination-text-area)))

(defn create-destination-panel []
  (seesaw-core/vertical-panel 
    :id :north-panel 
    :items [(terms/destination-address) [:fill-v 3] (create-destination-text)]))

(defn create-peer-list-buttons []
  (seesaw-core/horizontal-panel :items 
    [ (seesaw-core/button :id :add-button :text (terms/add)) ]))

(defn create-peer-list-header-panel []
  (seesaw-core/border-panel
    :west (terms/peers)
    :east (create-peer-list-buttons)))

(defn create-peer-list-table []
  (seesaw-core/scrollable
    (seesaw-core/table :id :peer-table :preferred-size [600 :by 300]
      :model [ :columns peer-table-columns ])))

(defn create-peer-list-panel []
  (seesaw-core/border-panel
    :vgap 3
    :north (create-peer-list-header-panel)
    :center (create-peer-list-table)))

(defn create []
  (seesaw-core/border-panel
    :border 5
    :vgap 5
    :north (create-destination-panel)
    :center (create-peer-list-panel)))