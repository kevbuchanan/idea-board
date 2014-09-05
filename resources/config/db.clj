{:development {:db "waza_development"
               :user "root"
               :make-pool? true
               :test-connection-on-checkout true
               :useLegacyDatetimeCode false
               :serverTimezone "UTC"}
 :test {:db "waza_test"
        :useLegacyDatetimeCode false
        :serverTimezone "UTC"
        :user "root"
        :make-pool? true}
 }
