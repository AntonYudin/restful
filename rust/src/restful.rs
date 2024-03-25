use serde::{Deserialize, Serialize};

#[derive(Deserialize)]
pub struct Input {
    pub input: Option<String>
}

#[derive(Serialize, Deserialize)]
pub struct Output {
    pub content: String
}

