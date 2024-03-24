package output

type Output struct {
	XMLName	string `json:"-" xml:"output"`
	Value	string `json:"value" xml:"value"`
}

