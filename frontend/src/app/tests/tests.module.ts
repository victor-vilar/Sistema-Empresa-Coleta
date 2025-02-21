import { NgModule } from "@angular/core";
import { MockTestComponent } from "./mock-test.component";
import { MockCurrencyPipe } from "./mock-currency-pipe.pipe";

@NgModule({
    declarations: [
        MockTestComponent,
        MockCurrencyPipe,
    ],
    exports:[
        MockTestComponent,
        MockCurrencyPipe
    ]
})
export class TestModule{}