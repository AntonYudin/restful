use reqwest::Client;

use restful::restful::Output;


async fn fetch_process(
    url: &str,
    input: &str,
    callback: impl Fn(&str) -> Output
) -> Result<Output, reqwest::Error> {

    let url_with_param = format!("{}?input={}", url, input);

    let response = Client::builder()
        .danger_accept_invalid_certs(true)
        .build()
        .unwrap()
        .get(&url_with_param)
        .send()
        .await?
    ;

    let body = response.text().await?;

    Ok(callback(&body))
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {

    match fetch_process(
        "https://127.0.0.1:8080/api/processJSON",
        "test",
        |body| {
            let result: Output = serde_json::from_str(&body).unwrap();
            return result;
        }
    ).await {
        Ok(data) => println!("JSON: {}", data.content),
        Err(e) => eprintln!("Error fetching JSON data: {}", e),
    }

    match fetch_process(
        "https://127.0.0.1:8080/api/processXML",
        "test",
        |body| {
            let result: Output = serde_xml_rs::from_str(&body).unwrap();
            return result;
        }
    ).await {
        Ok(data) => println!("XML: {}", data.content),
        Err(e) => eprintln!("Error fetching XML data: {}", e),
    }

    Ok(())
}

