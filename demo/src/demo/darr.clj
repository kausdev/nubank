(ns demo.darr
  (:require
    [clojure.string     :as str]
    [cooljure.parse     :as coolp]
    [schema.core        :as s]
    [schema.test        :as s-tst] )
  (:use 
    [cooljure.core] )
  (:gen-class))

(def Darr
  "A 2-D array of objects."
  [s/Any] )

(s/defn num-rows :- s/Int
  "Returns the number of rows of an Darr."
  [it :- Darr]
  (alength it))

(s/defn num-cols :- s/Int
  "Returns the number of cols of an Darr."
  [it :- Darr]
  (alength (aget it 0)))

(s/defn get-elem :- s/Any
  "Gets an Darr element"
  [ -array  :- Darr
    ii      :- s/Int
    jj      :- s/Int ]
  (aget -array ii jj))

(s/defn set-elem :- Darr
  "Puts a value into an Darr element, returning the updated Darr."
  [ -array  :- Darr
    ii      :- s/Int
    jj      :- s/Int
    newVal  :- s/Any]
  (aset -array ii jj (double newVal)))

(s/defn disp :- nil
  [-array :- Darr]
  (dotimes [ii (num-rows -array)]
    (dotimes [jj (num-cols -array)]
      (print (format "%8s" (get-elem -array ii jj))))
    (newline)))

(s/defn create :- Darr
  "([nrows ncols] [nrows ncols init-val])
  Return a new Darr of size=[nrows ncols] initialized to zero (or init-val if supplied)"
  ( [nrows :- s/Int 
     ncols :- s/Int]
    (make-array Double/TYPE nrows ncols)  ; default init to zero 
  )
  ( [nrows      :- s/Int 
     ncols      :- s/Int
     init-val   :- s/Num]
    (let [result  (make-array Double/TYPE nrows ncols) ]
      (dotimes [ii nrows]
        (dotimes [jj ncols]
;         (let [^doubles darr (aget ^objects result ii)] )
          (aset result ii jj init-val)))
      result
    )
  )
)

; #todo -> elem-set/elem-get


; (defn is-array? [x] 
;   (-> x class .isArray))