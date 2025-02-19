import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'pipename'})
export class MockPipe implements PipeTransform {
    transform(value: any): number {
        
        return value;
    }
}