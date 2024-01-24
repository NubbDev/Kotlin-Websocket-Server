use jni::objects::{JClass, JString};
use jni::sys::{jstring, JNI_VERSION_1_8};
use jni::JNIEnv;

pub mod construction;

#[derive(PartialEq)]
pub enum State {
    Bezier,
    // Line,
    Spline,
    Start,
    Check,
    // End,
}

#[derive(serde::Serialize, serde::Deserialize)]
pub struct Point {
    x: f32,
    y: f32,
    rot: f32,
}
impl Point {
    fn new(x: f32, y: f32, rot: f32) -> Point {
        Point { x, y, rot }
    }
    fn new_from_vec(vec: Vec<f32>) -> Point {
        Point {
            x: vec[0],
            y: vec[1],
            rot: vec[2],
        }
    }
}

#[derive(serde::Serialize)]
pub enum Command {
    Start(Point),
    Bezier(Vec<Point>),
    Goto(Point),
    // Line(Point, Point),
    Spline(Vec<Vec<Point>>),
    Wait(String),
    // End,
}

#[no_mangle]
pub extern "system" fn Java_ca_helios5009_CommandsParse_read<'local>(
    mut env: JNIEnv<'local>,
    _class: JClass<'local>,
    commands: JString<'local>,
) -> jstring {
    let path = env
        .get_string(&commands)
        .expect("Couldn't create java string!");

    let commands = path.to_str().unwrap().to_string();
    let parsed_commands = construction::parse_commands(commands);

    let json = serde_json::to_string(&parsed_commands).unwrap();
    let output = env.new_string(json).expect("Couldn't create java string!");

    output.into_raw()
}
