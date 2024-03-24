package main

import (
	"encoding/json"
	"encoding/xml"
	"fmt"
	"io/ioutil"
	"net/http"
	"crypto/tls"
)

import "main/output"

type Processor func (body []byte)

func process(client *http.Client, url string, processor Processor) {


	response, err := client.Get(url)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer response.Body.Close()

	body, err := ioutil.ReadAll(response.Body)
	if err != nil {
		fmt.Println("Error reading response body:", err)
		return
	}

	processor(body)

}

func main() {

	transport := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}

	client := &http.Client{Transport: transport}

	process(
		client,
		"https://localhost:8443/api/processJSON?input=test",
		func(body []byte) {
			var output output.Output
			err := json.Unmarshal(body, &output)
			if err != nil {
				fmt.Println("Error unmarsahlling JSON:", err)
				return
			}
			fmt.Printf("output.value: %v\n", output.Value)
		},
	)

	process(
		client,
		"https://localhost:8443/api/processXML?input=test",
		func(body []byte) {
			var output output.Output
			err := xml.Unmarshal(body, &output)
			if err != nil {
				fmt.Println("Error unmarsahlling XML:", err)
				return
			}
			fmt.Printf("output.value: %v\n", output.Value)
		},
	)

}

