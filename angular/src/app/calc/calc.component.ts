import { Component, OnInit } from '@angular/core';
import{HttpClient, HttpClientModule, HttpHeaders}from '@angular/common/http'

@Component({
  selector: 'app-calc',
  templateUrl: './calc.component.html',
  styleUrls: ['./calc.component.css']
})
export class CalcComponent implements OnInit {

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
  }

  
  public current = "0";
  public previous = "";
  public op = "";
  public last = "0";
  public result :any = 0;

  send(x : string){
    const headers = new HttpHeaders({'Content-Type' : "application/text"})
    this.http.post('http://localhost:8070/calculate/expression', x,
    {headers : headers, responseType : 'text'})
    .subscribe((response) => {
      this.result = response
      this.current = this.result.toString();
      if(this.last != "=" && this.last != "sqr=" && this.last != "sqrt=" && this.last != "invert=" && this.last != "%") this.previous = this.current + " " + this.last;
    })
  }

  appendNumber(number : string){
    if(this.last == "=" || this.current == "ERROR!" || this.current == "Cannot divide by zero") {
      this.previous = "";
      this.current = number;
    }
    else if(this.current == "0" && number == ".") this.current = "0.";
    else if(this.current == "0" || this.op == this.last) this.current = number;
    else this.current = this.current + number;
    this.last = number;
  }

  appendOperation(operation : string){
    if(this.current == "ERROR!" || this.current == "Cannot divide by zero") return;
    if(this.last == this.op) this.last = operation;
    else if(this.last == "=") this.previous = this.current + " " + operation;
    else this.previous = this.previous + " " + this.current + " " + operation;
    this.send(this.previous);
    this.op = operation;
    this.last = operation;
  }

  equal(){
    if(this.previous.charAt(this.previous.length-1) == "=" || this.current == "ERROR!" || this.current == "Cannot divide by zero") return;
    this.previous = this.previous + " " + this.current + " =";
    this.send(this.previous);        
    this.last = "="; 
  }

  delete(){
    if(this.current == "ERROR!" || this.current == "Cannot divide by zero") this.clearAll();
    else if(this.last == this.op || this.last == "=") return;
    if(this.current.length == 1 || (this.current.length == 2 && this.current[0] == "-")) this.current = "0";
    else if(this.current.length > 1)
    this.current = this.current.substr(0,this.current.length-1);
  }

  clearAll(){
    this.current = "0";
    this.previous = "";
    this.op = "";
    this.last = "0";
    this.result = 0;
  }
  
  square(){
    if(this.current == "ERROR!" || this.current == "Cannot divide by zero") return;
    if(this.previous == "" || this.last == "=") this.previous = "sqr(" + this.current + ")";
    this.last = "=";
    this.send(this.current+"ss");
  }

  root(){
    if(this.current == "ERROR!" || this.current == "Cannot divide by zero") return;
    if(this.previous == "" || this.last == "=") this.previous = "sqrt(" + this.current + ")"
    this.last="=";
    this.send(this.current+"rr");
  }

  invert(){
    if(this.current == "ERROR!" || this.current == "Cannot divide by zero") return;
    if(this.previous == "" || this.last == "=" ) this.previous = "1/" + this.current
    this.last = "=";
    this.send(this.current +"nn");
  }

  negative(){
    if(this.current == "ERROR!" || this.current == "Cannot divide by zero") return;
    let temp : number = parseFloat(this.current);
    temp = temp*-1;
    this.current = temp.toString();
  }

  percentage(){
    if(this.current == "ERROR!" || this.current == "Cannot divide by zero") return;
  
  this.last = "%";
  this.previous = ""
  this.send(this.current+"%%");
  }

  
}
