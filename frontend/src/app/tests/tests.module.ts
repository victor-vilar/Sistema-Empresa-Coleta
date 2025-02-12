import { NgModule } from "@angular/core";
import { MockTestComponent } from "./mock-test.component";

@NgModule({
    declarations: [
        MockTestComponent
    ],
    exports:[
        MockTestComponent
    ]
})
export class TestModule{}