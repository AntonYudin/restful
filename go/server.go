package main

import (
	"encoding/json"
	"encoding/xml"
	"fmt"
	"log"
	"net/http"
)

import "main/output"


func jsonHandler(writer http.ResponseWriter, request *http.Request) {
	input := request.URL.Query().Get("input")
	output := output.Output{Value: "output [" + input + "]"}
	fmt.Printf("jsonHandler.output: %v\n", output);

	writer.Header().Set("Content-Type", "application/json")
	if err := json.NewEncoder(writer).Encode(output); err != nil {
		http.Error(writer, "Error encoding JSON", http.StatusInternalServerError)
		return
	}
	println("done.")
}

func xmlHandler(writer http.ResponseWriter, request *http.Request) {
	input := request.URL.Query().Get("input")
	output := output.Output{Value: "output [" + input + "]"}
	fmt.Printf("xmlHandler.output: %v\n", output);

	writer.Header().Set("Content-Type", "application/xml")
	if err := xml.NewEncoder(writer).Encode(output); err != nil {
		http.Error(writer, "Error encoding XML", http.StatusInternalServerError)
		return
	}
	println("done.")
}


func main() {
	http.HandleFunc("/api/processJSON", jsonHandler)
	http.HandleFunc("/api/processXML", xmlHandler)

	log.Fatal(http.ListenAndServeTLS(":8443", "server.crt", "server.key", nil))
}

