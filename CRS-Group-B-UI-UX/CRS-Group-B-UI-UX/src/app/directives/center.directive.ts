import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appCenter]'
})
export class CenterDirective {
el: ElementRef;
  constructor(el: ElementRef) { 
    this.el = el;
  }

  ngOnInit(){
    this.el.nativeElement.style.textAlign ="center";
  }

}
