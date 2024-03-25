
use actix_web::{web, App, HttpResponse, HttpServer, Responder};
use serde::{Deserialize, Serialize};
use serde_xml_rs::to_string;
use openssl::ssl::{SslAcceptor, SslFiletype, SslMethod};


#[derive(Deserialize)]
struct Input {
    input: Option<String>
}

#[derive(Serialize)]
struct Output {
    content: String
}

async fn process_json(query: web::Query<Input>) -> impl Responder {
    HttpResponse::Ok().json(
        Output {
            content: "[".to_owned() +
                match &query.input {
                    Some(value) => value,
                    None => &"<none>"
                } + "]"
        }
    )
}

async fn process_xml(query: web::Query<Input>) -> impl Responder {
    HttpResponse::Ok().content_type("application/xml").body(
        to_string(
            &Output {
                content: "[".to_owned() +
                    match &query.input {
                        Some(value) => value,
                        None => &"<none>"
                    } + "]"
            }
        ).unwrap()
    )
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {

    let mut builder = match SslAcceptor::mozilla_intermediate(SslMethod::tls()) {
        Err(err) => panic!("error: {}", err),
        Ok(builder) => builder
    };

    if let Err(err) = builder.set_private_key_file("server.key.pem", SslFiletype::PEM) {
        panic!("error: {}", err);
    }

    if let Err(err) = builder.set_certificate_chain_file("server.cert.pem") {
        panic!("error: {}", err);
    }

    HttpServer::new(|| {
        App::new()
            .route("/api/processJSON", web::get().to(process_json))
            .route("/api/processXML", web::get().to(process_xml))
    }).bind_openssl("127.0.0.1:8080", builder)?
        .run()
        .await
}

