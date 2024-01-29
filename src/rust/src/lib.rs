use construction::command_read;
use jni::{
    objects::{JClass, JString},
    sys::jstring,
    JNIEnv,
};
use serde::{Deserialize, Serialize};

pub mod construction;

#[derive(Serialize, Deserialize, PartialEq, Clone)]
pub struct Event {
    message: String,
}

#[derive(Serialize, Deserialize, PartialEq, Clone)]
pub struct Point {
    x: f64,
    y: f64,
    rot: f64,
    event: Event,
}

#[derive(Serialize, Deserialize, PartialEq)]
pub struct Line {
    next: Point,
}

#[derive(Serialize, Deserialize, PartialEq, Clone)]
pub struct Bezier {
    start: Point,
    end: Point,
    control: Vec<Point>,
}

#[derive(Serialize, Deserialize, PartialEq)]
pub struct Wait {
    wait_type: WaitType,
    event: Event,
}

impl Line {
    fn new(next: Point) -> Line {
        Line { next }
    }
}

impl Point {
    fn new(x: f64, y: f64, rot: f64, event: Event) -> Point {
        Point { x, y, rot, event }
    }

    fn new_from_vec(vec: Vec<f64>, event: Event) -> Point {
        Point {
            x: vec[0],
            y: vec[1],
            rot: vec[2],
            event,
        }
    }
}

impl Bezier {
    fn new() -> Bezier {
        Bezier {
            start: Point::new(0.0, 0.0, 0.0, Event::new("".to_string())),
            end: Point::new(0.0, 0.0, 0.0, Event::new("".to_string())),
            control: vec![],
        }
    }

    fn start(&mut self, start: Point) {
        self.start = start;
    }

    fn end(&mut self, end: Point) {
        self.end = end;
    }

    fn add_control(&mut self, control: Point) {
        self.control.push(control);
    }
}

impl Event {
    fn new(message: String) -> Event {
        Event { message }
    }
}

impl Wait {
    fn new(wait_type: WaitType, event: Event) -> Wait {
        Wait { wait_type, event }
    }
}

#[derive(Serialize, Deserialize, PartialEq)]
pub enum Command {
    Start(Point),
    End(Event),
    Line(Line),
    Bezier(Bezier),
    Spline(Vec<Spline>),
    Wait(Wait),
}

#[derive(Serialize, Deserialize, PartialEq)]
pub enum Spline {
    Bezier(Bezier),
    Wait(Wait),
}

#[derive(Serialize, Deserialize, PartialEq)]
pub enum WaitType {
    Time(u64),
    Event(Event),
}

#[derive(PartialEq)]
pub enum CommandState {
    Start,
    Reading,
    End,
    None,
}

#[no_mangle]
pub extern "system" fn Java_ca_helios5009_CommandsParse_read<'local>(
    mut env: JNIEnv<'local>,
    _class: JClass<'local>,
    commands: JString<'local>,
) -> jstring {
    let content: String = env
        .get_string(&commands)
        .expect("Couldn't get java string!")
        .into();
    let parse_commands = command_read(content);

    let result = env
        .new_string(parse_commands.to_string())
        .expect("Couldn't create java string!");

    result.into_raw()
}
